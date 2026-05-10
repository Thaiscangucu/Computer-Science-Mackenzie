
A1P1 = [7,8,9,6,7,8,6,9,10,9]
A1P2 = [5,6,7,9,5,4,5,2,1,8]
A2P1 = [4,3,7,8,9,0,5,3,1,9]
A2P2= [1,3,5,6,9,0,4,6,1,10]
P1 = [4,5,5,6,7,3,2,1,10,9]
P2 = [4,6,7,4,5,6,3,3,9,10]
NP= [0.4,0.2,0.3,0.5,0.2,0.0,0.2,0.1,0.5,0.5]

n = 10
Ativ1=[0,0,0,0,0,0,0,0,0,0]
for i in range(n):
    Ativ1[i] = (A1P1[i]+A1P2[i]) / 2

Ativ2=[0,0,0,0,0,0,0,0,0,0]
for i in range(n):
    Ativ2[i] = (A2P1[i]+A2P2[i]) / 2

    N1 = [0,0,0,0,0,0,0,0,0,0]
for i in range(n):
    N1[i] = 0.7 * P1[i] + 0.3 * Ativ1[i]

    N2 = [0,0,0,0,0,0,0,0,0,0]
for i in range(n):
    N2[i] = 0.7 * P2[i] + 0.3 * Ativ2[i]

    NSP = [0,0,0,0,0,0,0,0,0,0]
for i in range(n):
    NSP[i] = (N1[i] + N2[i]) / 2

NF = [0,0,0,0,0,0,0,0,0,0]
for i in range(n):
    NF[i] = NSP[i] + NP[i]

print("Média: ", NF)

def mediana(v):
m = len(v)
vo = sorted(v)
if m % 2 == 0:
    mi = int(m/2)
    mediana = (vo[mi - 1] + vo[mi]) / 2
else:
    mi = int(m/2)
    mediana = vo[mi]
return mediana 
print(mediana(NF))

