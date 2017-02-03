%dw 1.0
%output application/java
---
payload.id[0] when ((sizeOf payload)>0) otherwise null