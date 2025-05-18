# 📘 Linguagem **Kote** - Linguagem de programação baseada na série de livros 'As crônicas do Matador do Rei'

---

## ✨ Visão Geral

**Kote** é uma linguagem simbólica e expressiva, inspirada por temas místicos e com sintaxe única, projetada para ser traduzida automaticamente para **Pascal**. Sua proposta é oferecer uma estrutura intuitiva e poética para construção de algoritmos básicos.

---

## 🛠 Palavras-chave reservadas

| Palavra-chave     | Significado             |
|-------------------|--------------------------|
| `vincular`        | Declaração de variável   |
| `alar`            | Início de um `if`        |
| `segunda_voz`     | `else if`                |
| `desdobramento`   | `else`                   |
| `cantar`          | `print` (saída)          |
| `inscrever`       | `read` (entrada)         |
| `compasso`        | `for` loop               |
| `invocar`         | Chamada de função        |
| `nomear`          | Declaração de função     |
| `com`             | Parâmetros               |
| `INT`, `FLOAT`, `STRING` | Tipos de variável |

---

## 🧪 Exemplo de Código

### 📌 Declaração + Loop `for` com print:

```kote
vincular i = int;

compasso ( i = 10; 1 ) {
  cantar ( i )
}
```
---

## ✅ Estruturas da Linguagem
### 🧾 Variáveis

```kote
vincular idade = int;
```
### 🖨️ Saída, 📥 Entrada
```kote
cantar ( idade );
inscrever ( idade );
```
### 🧠 Condicional (If)
```
( idade > 18 ) alar {
  cantar ( "maior de idade" )
}
desdobramento {
  cantar ( "menor" )
}
```
---
## 📚 Execução
1 - Escreva um programa em .kote

2 - Compile com:
```bash
fpc saida.pas
```

3 - Rode com:
```bash
saida
```
