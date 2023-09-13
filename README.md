Allezon Analytics Platform
==========================

# Overview

We are going to create a data-collection and analytics platform for a big online retailer Allezon. Users' actions on the Allezon site are sent to us as VIEW and BUY events containing information about the user and the product they interacted with. In order to solve Allezon's business use cases our platform has to collect these data and provide endpoints answering specific queries.

# Deployment
Create `.env` file with a single line `PASSWORD=<password>` and run `./setup.sh && ./deploy.sh`.

`setup.sh` is used for:
* Setting up network between virtual machines
* Downloading dependencies, including Maven and Java
* Creating Kafka and Aerospike clusters

`deploy.sh` is used for:
* Building JARs
* Deploying HAProxy
* Deploying 2 instances of each API:
    * User tags API;
    * User profiles API;
    * Aggregates API;
* Deploying 2 instances of Kafka workers:
    * User profiles loader (Kafka Consumer);
    * Aggregates loader (Kafka Streams Processor)

# Use Cases

Input events we are going to work with represent users' actions on the Allezon site and are called user tags. There are two types of actions, VIEW when the user visits some product page and BUY when they decide to make a purchase. Our primary goal is to collect those input events and then respond to the queries according to their specification. All communication with our platform should happen via HTTP requests.

## Data Format

User actions (i.e. VIEWs and BUYs) are sent as user tag events in the following format:

```
<user_tag>

{
    "time": string,   // format: "2022-03-22T12:15:00.000Z"
                      //   millisecond precision
                      //   with 'Z' suffix
    "cookie": string,
    "country": string,
    "device": PC | MOBILE | TV,
    "action": VIEW | BUY,
    "origin": string,
    "product_info": {
        "product_id": string,
        "brand_id": string,
        "category_id": string,
        "price": int32
    }
}
```

## Use Case 1: Adding User Tags

We should provide an endpoint that allows adding user tag events, one at a single HTTP request. Storing these data in an efficient and reliable manner makes a foundation for the further use cases.
### Requirements

* max throughput: 1000 req/s
* request timeout: 200 ms
* Losing events may cause producing inaccurate answers to the queries.

### Data Characteristics

* number of unique cookies ~= 1_000_000
* number of unique countries = 100
* number of unique devices = 3
* number of unique actions = 2
* number of unique origins = 1_000
* number of unique brands = 250
* number of unique product_categories = 67

## Use Case 2: Getting User Profiles

### Business Context

Allezon plans to build a recommendation engine in the near future. At the start of this initiative they want to have quick access to the user profiles (history of the user actions). For each user you can keep their 200 most recent events of each type.

### Requirements

* max throughput: 100 req/s
* request timeout: 200 ms
* data latency: 10 s
  * i.e. we can query about events published more than 10 s ago
* We have to keep at least 200 most recent VIEWs and at least 200 most recent BUYs for each cookie (older events can be discarded).
  * There is a separate limit for each action type.

## Use Case 3: Aggregated User Actions (Statistics)

### Business Context

Executives at Allezon want to view historical data from different perspectives. There is a broad category of queries for which aggregating results in **1 minute buckets** is satisfactory. Such queries will allow us to present various metrics over longer periods of time enabling us to visualize trends such as: users' buying patterns, performance of ad campaigns, etc. Aggregates should become available in query results soon after their time buckets are closed (in a matter of minutes). There is no predefined list of metrics which are going to be visualized.
We want to get aggregated stats about user actions matching some criteria and grouped by 1 minute buckets from the given time range.

### Requirements

* max throughput: 1 req/s
* request timeout: 60 s
* data latency: 1 m
  * i.e. we can query about events published more than 1 minute ago
