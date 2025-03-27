import paho.mqtt.client as mqtt

# MQTT broker settings
BROKER = 'localhost'
PORT = 9001  # default MQTT port, change if needed
USERNAME = 'demo_user_1'
PASSWORD = 'p4ssw0rd'
TOPIC = 'test/topic'
MESSAGE1 = 'Hello, MQTT! '

# Create an MQTT client instance
client = mqtt.Client(transport='websockets')

# Set username and password for authentication
client.username_pw_set(USERNAME, PASSWORD)

# Connect to the MQTT server
client.connect(BROKER, PORT, 60)

number = 0
# Publish the message
while True:
    MESSAGE = MESSAGE1 + f'{number}'
    client.publish(TOPIC, MESSAGE)
    number += 1
    print(f"Message '{MESSAGE}' published to topic '{TOPIC}'\n")
    for x in range(9999999): # to slow down the code
        pass
# Disconnect from the server
client.disconnect()


