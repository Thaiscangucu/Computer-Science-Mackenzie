import math
import scipy.stats as st

print("ex 16.26")
print("a)")
list = [-8.3, -7.0, -7.8, -6.2, -6.5, -6.5, -6.8, -6.8, -5.3, -5.2, -5.7, -5.2, -5.6, -5.9, -5.3, -5.1, -4.7, -4.9,
        -4.3, -4.4, -4.0, -4.9, -4.7, -3.1, -3.0, -3.6, -3.3, -3.8, -2.5, -2.7, -2.1, -2.2, -2.0, -2.1, -2.5, -2.3,
        -2.2, -1.0, -1.8, -1.0, -0.8, -0.3, 0.3, 0.4, 0.2, 1.7, 2.2]
new_list = []
for n in list:
    numb = str(n).split(".")
    if not numb[0] in [i[0] for i in new_list]:
        new_list.append(numb)
    else:
        new_list[[i[0] for i in new_list].index(numb[0])].append(numb[1])

for i in new_list:
    c = 0
    for j in i:
        if c == 0:
            print(j, end='| ')
            c = 1
        else:
            print(j, end=' ')
    print(" ")

n = 47
dp = 2.5
x = sum(list) / n
z = 2.576
erro = z * (dp / math.sqrt(n))
n1 = round(x + erro, 3)
n2 = round(x - erro, 3)
print(f"\nb) {n1} <= µ <= {n2}")

print("c) Sim, pois foi calculado o intervalo com o zscore de 99%, então isso significa que nós estaremos conifantes")
print("sobre 99% de todas as amostrasa aleatórias.\n")

print("ex 16.27")
print("a)")
n = 10
list = [30, 30, 42, 35, 22, 33, 31, 29, 19, 23]
print("1 | 9")
print("2 | 2 9 3")
print("3 | 0 0 5 3 1")
print("4 | 2")

dp = 7
z = 1.960
erro = z * (dp / math.sqrt(n))
x = sum(list) / n
n1 = round(x + erro, 3)
n2 = round(x - erro, 3)
print(f"\nb) {n2} <= µ <= {n1}")


print("\n17.28")
n = 463
x = 13.7
dp = 7.4
u = 13
print("a) Ho = µ = 13 e Ha = µ > 13.")

z = round((x - u) / (dp / math.sqrt(n)), 3)
print(f"b) O valor de z = {z}")

score = round(st.norm.cdf(z), 4)
print(f"c) O valor P de z = {score}. Sim é possível concluir que os estudantes realmente estudem mais de 13 horas por semana.")

print("\n17.38")
list = [33190, 31860, 32590, 26520, 33280, 32320, 33020, 32030, 30460, 32700, 23040, 30930, 32720, 33650, 32340, 24050, 30170, 31300, 28730, 31920]
dp = 3000
n = 20
u = 32500
media = int(sum(list) / n)
z = round((media - u) / (dp / math.sqrt(n)), 2)
score = round(st.norm.cdf(z), 4)

print(f"a) O zscore de {z} = {score}. Então sim, há evidência estatisticamente.")

u = 31500
z = (media - u) / (dp / math.sqrt(n))
score_de_z = round(st.norm.cdf(-0.98), 4)
print(f"b) O zscore de -0.98 = {score_de_z}. Portanto não há evidência estatisticamente.")

print("\n17.40")
list = [30, 30, 42, 35, 22, 33, 31, 29, 19, 23]
n = 10
media = sum(list) / n
print(f"R: Sim, a média dessa amostra de 10 estudantes não treinados é de {media}µg/L.")

print("\n17.41")
list = [0.07, 0.64, -0.12, -0.05, -0.18, 0.14, -0.16, 0.03, 0.05, 0.02, 0.43, 0.24, -0.11, 0.28, 0.05, 0.29]
n = len(list)
media = round(sum(list) / n, 5)
dp = 0.22

print("a) Ho = µ = 0 e Ha = µ > 0. Sendo µ a diferença média da sensibilidade na população total de pessoas que usam a graxa embaixo dos olhos.")

z = round((media - 0) / (dp / math.sqrt(n)), 2)
score = round(st.norm.cdf(z), 4)
print(f"b) z = {z} e alpha = 0.05. Então não há evidência significante que a graxa aumenta a sensibilidade.")
