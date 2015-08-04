int led5 = 5;
int led6 = 6;
int led7 = 7;
char val = '0';

void setup() {
  Serial.begin(115200);
  
  pinMode(led5, OUTPUT);
  pinMode(led6, OUTPUT);
  pinMode(led7, OUTPUT);
  
  digitalWrite(led5, LOW);
  digitalWrite(led6, LOW);
  digitalWrite(led7, LOW);
}

void loop() {
  val = Serial.read();
  
  if(val == 'led5') {
    digitalWrite(led5, HIGH);
    digitalWrite(led6, LOW);
    digitalWrite(led7, LOW);
  }
  
  if(val == 'led6') {
    digitalWrite(led6, HIGH);
    digitalWrite(led5, LOW);
    digitalWrite(led7, LOW);
  }  
  
  if(val == 'led7') {
    digitalWrite(led7, HIGH);
    digitalWrite(led6, LOW);
    digitalWrite(led5, LOW);
  }  
}
