#6. Faça um programa que leia uma temperatura em graus Celsius e apresente-a convertida em graus Fahrenheit. A fórmula de conversão é:
# F = ((9 * C) + 160) / 5 na qual F é a temperatura em Fahrenheit e C é a temperatura em Celsius.
c = float(input("Temperatura em C°: "))
f = ((9 * c) + 160)/5
print("Temperatura em F°: ", f)

f = float(input("Temperatura em F°:"))
c = ((f - 32) / 1.8)
print("Temperatura em C°:", c)

