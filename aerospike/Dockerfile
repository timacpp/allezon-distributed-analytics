FROM aerospike/aerospike-server:latest
COPY aerospike.conf /etc/aerospike/aerospike_custom.conf
ENTRYPOINT /usr/bin/asd --config-file /etc/aerospike/aerospike_custom.conf