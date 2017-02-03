%dw 1.0
%output application/java
%var resourceUrl="http://" ++ p('http.openbank.system.b.host') ++ ":" ++ p('http.openbank.system.b.port') ++ p('http.openbank.system.b.basePath') ++ "/accounts" ++ "?ssn=" ++ flowVars.ssn
---
(payload map ((payload01 , indexOfPayload01) -> {
	uuid: payload01.uuid,
     id: payload01.id,
     this_account: payload01.this_account,  
     other_account: payload01.other_account,  
     details: payload01.details,
     resource_url: resourceUrl
})) when (payload != null) otherwise []