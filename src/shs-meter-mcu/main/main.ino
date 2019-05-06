#include <Wire.h>
#include <LiquidCrystal.h>
#include <MsTimer2.h>

LiquidCrystal lcd(0x3F, 20,4);

int kWh = 0;
float kW = 0;
int blinks = 0;
int blinksIn10 = 0;
long duration;

void setup() {
  Serial.begin(9600);
  pinMode(7, INPUT_PULLUP);

  lcd.init();
  lcd.clear();
  lcd.backlight();

  MsTimer::set(10000, MsTimer);
  MsTimer::start();
}

void loop() {
  // put your main code here, to run repeatedly:

}
