int led5 = 5;
int led6 = 6;
int led7 = 7;

String content = "";
char character;
  
void setup() {
  Serial.begin(115200);
  
  pinMode(led5, OUTPUT);
  pinMode(led6, OUTPUT);
  pinMode(led7, OUTPUT);
  
  digitalWrite(led5, LOW);
  digitalWrite(led6, LOW);
  digitalWrite(led7, LOW);
}

void readFromSerial() {
  if(Serial.available() >= 4) {
   for (int i=0; i < 4; i++)  {
     character = Serial.read();
     content.concat(character);
   }
  }
}

void loop() {
  content = "";
  readFromSerial();
  
  if(content == "led5") {
    Serial.print(content);
    digitalWrite(led5, HIGH);
    digitalWrite(led6, LOW);
    digitalWrite(led7, LOW);
  }
  
  if(content == "led6") {
    Serial.print(content);
    digitalWrite(led6, HIGH);
    digitalWrite(led5, LOW);
    digitalWrite(led7, LOW);
  }  
  
  if(content == "led7") {
    Serial.print(content);
    digitalWrite(led7, HIGH);
    digitalWrite(led6, LOW);
    digitalWrite(led5, LOW);
  }
}
