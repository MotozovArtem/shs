#include <ArduinoHttpClient.h>
#include <b64.h>
#include <Ethernet.h>
#include <EthernetClient.h>
#include <SPI.h>

#include "Record.h"

byte mac[] = {0x90, 0xA2, 0xDA, 0x00, 0x4A, 0xE0};
const char headControllerUrl[] = "192.168.1.3";
const int port = 8080;
const int networkTimeout = 30 * 1000;
const int networkDelay = 1000;
const int sensorPin = 2;
int blinks = 0;
int kW = 0;

void setup()
{
    pinMode(sensorPin, INPUT_PULLUP);

    Serial.begin(9600);
    Serial.println("Starting single datastream upload to Head Controller");
    Serial.println();
    while (Ethernet.begin(mac) != 1)
    {
        Serial.println("Error getting IP address via DHCP, trying again...");
        delay(15000);
    }
    Serial.print("Local IP: ");
    Serial.println(Ethernet.localIP());
}

void loop()
{
    int sensorValue = analogRead(sensorPin);
    Serial.print("Sensor value: ");
    Serial.println(sensorValue);
    if (sensorValue > 600)
    {
        blinks++;
    }
    if (blinks == 10)
    {
        Serial.println("kW++");
        kW++;
        blinks = 0;
        if (kW % 2 == 0 && kW != 0)
        {
            Record record;
            record.value = kW;
            record.toJson();
            Serial.println(record.result);
            EthernetClient ethClient;
            HttpClient httpClient(ethClient, headControllerUrl, port);
            String contentType = "application/json";
            httpClient.post("/api/v1/record/", contentType, record.result);
            int statusCode = httpClient.responseStatusCode();
            String response = httpClient.responseBody();
            Serial.print("Status code: ");
            Serial.println(statusCode);
            Serial.print("Response: ");
            Serial.println(response);
        }
    }
    delay(1000);
}