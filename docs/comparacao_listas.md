# Comparação: Lista Duplamente Encadeada com e sem Sentinelas

## Estrutura com Sentinela

✅ Vantagens:
- Menos casos especiais: nunca há `null` nos ponteiros `anterior` e `proximo`.
- Código mais limpo e conciso.

⚠️ Desvantagens:
- Ocupa um nó a mais, mesmo vazio.
- Pode confundir iniciantes devido ao nó extra.

---

## Estrutura sem Sentinela

✅ Vantagens:
- Mais próxima de implementações didáticas e intuitivas.
- Pouco uso de memória extra.

⚠ Desvantagens:
- Mais propensa a erros com `null`.
- Requer mais verificações de borda (vazia, primeiro, último...).

---

## Conclusão

A versão **com sentinela** é mais robusta e segura para grandes projetos. A versão **sem sentinela** é mais simples para aprendizado e uso pontual.
