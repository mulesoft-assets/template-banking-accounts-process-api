%dw 1.0
%output application/java
%var resourceUrl="http://" ++ p('http.openbank.system.a.host') ++ ":" ++ p('http.openbank.system.a.port') ++ p('http.openbank.system.a.basePath') ++ "/users/" ++ flowVars.userId ++ "/accounts" when (payload != null) otherwise null
---
(payload map ((payload01 , indexOfPayload01) -> {
	IBAN: payload01.IBAN,
	available_balance: payload01.available_balance,
	balance: payload01.balance,
	bank_id: payload01.bank_id,
	id: payload01.id,
	label: payload01.label,
	overdraft: payload01.overdraft,
	swift_bic: payload01.swift_bic,
	type: payload01.type,
	resource_url: resourceUrl
})) when (payload != null) otherwise []