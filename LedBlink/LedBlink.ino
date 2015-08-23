int led5 = 5;
int led6 = 6;
int led7 = 7;

int len = 4;
char input[4];
  
void setup() {
  Serial.begin(115200);
  
  pinMode(led5, OUTPUT);
  pinMode(led6, OUTPUT);
  pinMode(led7, OUTPUT);
  
  digitalWrite(led5, LOW);
  digitalWrite(led6, LOW);
  digitalWrite(led7, LOW);
}

String readBytesFromSerial() {
  Serial.readBytes(input, len);
  return String(input);
}

void loop() {
  String content = readBytesFromSerial();
  Serial.print(content);
  
  if(content == "led5") {
    digitalWrite(led5, HIGH);
    digitalWrite(led6, LOW);
    digitalWrite(led7, LOW);
  }
  
  if(content == "led6") {
    digitalWrite(led6, HIGH);
    digitalWrite(led5, LOW);
    digitalWrite(led7, LOW);
  }  
  
  if(content == "led7") {
    digitalWrite(led7, HIGH);
    digitalWrite(led6, LOW);
    digitalWrite(led5, LOW);
  }
}
