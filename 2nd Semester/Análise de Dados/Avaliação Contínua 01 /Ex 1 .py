import statistics as stat
from scipy.stats import variation
import numpy as np 
import pandas as pd
from matplotlib import pyplot as plt
import math

print("Exercício 1")
print("b) Funções pré programadas:")

idade = [18,20,19,21,25,22,18,20,21,19,20,21,19,24,21]
n = 15
print("Idade de 15 alunos: ", idade)

#Média
media = stat.mean(idade)
print("Média:", "%.1f" % media)

#Mediana
mediana = stat.median(idade)
print("Mediana:", mediana)

#Variância populacional
varianciap = stat.pvariance(idade)
print("Variância populacional:", "%.3f" % varianciap)

#Variância amostral
varianciaA = stat.variance(idade)
print("Variância amostral:", "%.9f" % varianciaA)

#Desvio padrão populacional
dpp = stat.pstdev(idade)
print("Desvio padrão populacional:", "%.3f" % dpp)

#Desvio padrão amostral
dpa = stat.stdev(idade)
print("Desvio padrão amostral:", "%.3f" % dpa)

#Coeficiente de variação populacional
cvp = variation(idade)
print("Coeficiente de variação populacional: ", cvp)

#Coeficiente de variação amostral
def cva(v):
  cva = dpa/media*100
  return cva
print("Coefiente de variação amostral:" "%.2f" % cva(idade), "%")

#Quartil 1
Q1 = np.quantile(idade, 0.25)
print("Quartil 1:", Q1) 

#Quartil 3
Q3 = np.quantile(idade, 0.75)
print("Quartil 3:", Q3)



print("c) Funções programadas pelo estudante:")

#Média:
def media(v):
  n = len(v)
  soma = 0
  for i in range(n):
      soma = soma + v[i]
  soma = soma / n
  return soma
print("Média:", "%.2f" %media(idade))


#Mediana:
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


#Variância populacional:
def varianciaP(v):
  n = len(idade)
  m = media(idade)
  soma = 0
  for i in range(n):
      soma = soma + (v[i] - m)**2
  soma = soma / n
  return soma
print("Variância populacional:", "%.3f" % varianciaP(idade))


#Variância amostral:
def varianciaA(v):
  n = len(idade)
  m = media(idade)
  soma = 0
  for i in range(n):
      soma = soma + (v[i] - m)**2
  soma = soma / (n-1)
  return soma
print("Variância amostral:", "%.3f" % varianciaA(idade))


#Desvio padrão populacional:
def dpp(v):
  vp = varianciaP(idade)
  dpP = math.sqrt(vp)
  return dpP
print("Desvio padrão populacional:", "%.3f" % dpp(idade))

#Desvio padrão amostral:
def dpa(x):
  va = varianciaA(idade)
  dpa = math.sqrt(va)
  return dpa
print("Desvio padrão amostral:", "%.3f" % dpa(idade))


#Coefiente de variação populacional:
def cvp(v):
  cvp = (dpp(idade)/media(idade))*100
  return cvp
print("Coefiente de variação populacional:" "%.2f" % cvp(idade), "%")

#Coefiente de variação amostral:
def cva(v):
  cva = (dpa(idade)/media(idade))*100
  return cva
print("Coefiente de variação amostral:" "%.2f" % cva(idade), "%")


#Quartil 1:
posicao_mediana = int((n+1)/2)
ordem = sorted(idade)
metade1 = ordem[0:posicao_mediana]
q1 = mediana(metade1)
print("Quartil 1: ", q1)


#Quartil 3:
posicao_mediana = int((n+1)/2)
ordem = sorted(idade)
metade2 = ordem[posicao_mediana:]
q3 = mediana(metade2)
print("Quartil 3: ",q3)

#Histograma
plt.hist(idade, 8, rwidth = 0.9)
plt.xlabel('Idade')
plt.ylabel('Quantidade de alunos')
plt.show()

#Boxplot (só aparece quando o histograma não está no código)
plt.boxplot(idade)
plt.xlabel('Idade')
plt.show()



