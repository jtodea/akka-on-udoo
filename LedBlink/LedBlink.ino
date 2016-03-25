int led5 = 5;
int led6 = 6;
int led7 = 7;

char content;
  
void setup() {
  Serial.begin(9600);
  
  pinMode(led5, OUTPUT);
  pinMode(led6, OUTPUT);
  pinMode(led7, OUTPUT);
  
  digitalWrite(led5, LOW);
  digitalWrite(led6, LOW);
  digitalWrite(led7, LOW);
}

void readFromSerial() {
  if(Serial.available() > 0) {
     content = Serial.read();
   }
  }
}

void loop() {
  readFromSerial();
  
  if(content == '5') {
    Serial.print(content);
    digitalWrite(led5, HIGH);
    digitalWrite(led6, LOW);
    digitalWrite(led7, LOW);
  }
  
  if(content == '6') {
    Serial.print(content);
    digitalWrite(led6, HIGH);
    digitalWrite(led5, LOW);
    digitalWrite(led7, LOW);
  }  
  
  if(content == '7') {
    Serial.print(content);
    digitalWrite(led7, HIGH);
    digitalWrite(led6, LOW);
    digitalWrite(led5, LOW);
  }

  if(content = '0') {
    Serial.print("Shutdown");
    digitalWrite(led7, LOW);
    digitalWrite(led6, LOW);
    digitalWrite(led5, LOW);
  }

}
