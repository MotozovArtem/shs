#include <Wire.h>
#include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd(0x3F, 20, 4);

int kWh = 0;
float kW = 0;
int blinks = 0;
int blinksIn10 = 0;
long duration;

void setup() {
  pinMode(7, INPUT_PULLUP);
}

void loop() {
  // put your main code here, to run repeatedly:

}
