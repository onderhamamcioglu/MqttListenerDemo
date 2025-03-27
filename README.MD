# MQTT Listener Demo Implementation With Java and Spring

## How to use?

### 1 - Run Mosquitto on Docker
- run `docker-compose --file .\docker\docker-compose.yaml up -d` to build and run mosquitto
- run `docker exec mosquitto mosquitto_passwd -U /etc/mosquitto/passwd` to encrypt passwords for mosquitto container

### 2 - Publish messages using python script
- install paho-mqtt before using by `pip install paho-mqtt`
- run `python .\publisher\publisher.py` to publish messages on mosquitto

### 3 - Run your Java code :)
- `gradle bootRun`
