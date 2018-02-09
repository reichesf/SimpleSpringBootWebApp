#!/bin/sh
#---------------------------------------------------------------------
# greeting.sh
#
# Shell script used to request a greeting from the service.
#---------------------------------------------------------------------

usage()
{
	echo ""
	echo "Usage: greeting.sh [-v] [-t mediaType] [-h host] [-p port]"
	echo "                   [-n name]"
	echo ""
	echo "           greeting.sh -t application/json -n steve"
	echo "           greeting.sh -t application/xml -n steve"
	echo ""
}

VERBOSE=
MEDIA_TYPE="application/xml"
HOST="localhost"
PORT="8080"
NAME=

while [ "$#" -gt 0 ]
do
	case $1 in
		-v) VERBOSE="-v" ; shift;;
		-h) shift ; HOST="$1" ; shift;;
		-p) shift ; PORT="$1" ; shift;;
		-t) shift ; MEDIA_TYPE="$1" ; shift;;
		-n) shift ; NAME="$1" ; shift;;
		*)  usage; exit 1;;
	esac
done

RESOURCE="greeting"

if [ "${NAME}" != "" ]
then
	RESOURCE="${RESOURCE}?name=${NAME}"
fi

curl ${VERBOSE}  \
	-X GET \
	--header accept:${MEDIA_TYPE} \
	http://${HOST}:${PORT}/${RESOURCE}

echo ""
