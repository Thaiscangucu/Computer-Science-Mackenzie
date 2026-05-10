import math
from matplotlib import pyplot as plt

idade = [18,20,19,21,25,22,18,20,21,19,20,21,19,24,21]
print(idade)
n = 15

#Média
def media(v):
  n = len(v)
  soma = 0
  for i in range(n):
      soma = soma + v[i]
  soma = soma / n
  return soma
print("Média:", "%.2f" %media(idade))

#Mediana
def mediana(v):
  n = len(v)
  vo = sorted(v)
  if n % 2 == 0:
      ni = int(n/2)
      mediana = (vo[ni - 1] + vo[ni]) / 2
  else:
      ni = int(n/2)
      mediana = vo[ni]
  return mediana
print("Mediana:", mediana(idade))

#Variância populacional
def varianciaP(v):
  n = len(idade)
  m = media(idade)
  soma = 0
  for i in range(n):
      soma = soma + (v[i] - m)**2
  soma = soma / n
  return soma
print("Variância populacional:", "%.3f" % varianciaP(idade))

#Variância amostral
def varianciaA(v):
  n = len(idade)
  m = media(idade)
  soma = 0
  for i in range(n):
      soma = soma + (v[i] - m)**2
  soma = soma / n-1
  return soma
print("Variância amostral:", "%.3f" % varianciaA(idade))

#Desvio padrão populacional
def dpp(v):
  vp = varianciaP(idade)
  dpP = math.sqrt(vp)
  return dpP
print("Desvio padrão populacional:", "%.3f" % dpp(idade))

#Desvio padrão amostral
def dpa(x):
  va = varianciaA(idade)
  dpa = math.sqrt(va)
  return dpa
print("Desvio padrão amostral:", "%.3f" % dpa(idade))

def desvio_padrao(x, n):
  m = media(idade)
  soma = 0
  for i in range(n):
      soma = soma + (x[i] - m)**2
  soma = math.sqrt(soma / (n - 1))
  return soma
print("Desvio padrão amostral:", "%.3f" % desvio_padrao(idade, n))


#Coefiente de variação populacional
def cvp(v):
  cvp = (dpp(idade)/media(idade))*100
  return cvp
print("Coefiente de variação populacional:" "%.2f" % cvp(idade), "%")

#Coefiente de variação amostral
def cva(v):
  cva = (dpa(idade)/media(idade))*100
  return cva
print("Coefiente de variação amostral:" "%.2f" % cva(idade), "%")


#Quartil 1
#def q1(v):
#  mediana = mediana(idade)
#  q1 = mediana(mediana)
#  return q1
#print("Quartil 1:", q1(idade))
#Quartil 3






  
