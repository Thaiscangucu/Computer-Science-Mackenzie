MaiorIdade = 0
MenorIdade = 0
percentual18e35 = 0
percentual65 = 0
mulheres = 0
MaisDe65 = 0
total = 0
idade = int(input("idade: "))

while idade != -1:
    sexo = input("sexo: ")
    total += 1

    if sexo == "F" and 18 <= idade <=35:
        mulheres += 1
    elif idade > 65:
        MaisDe65 += 1
    if idade > MaiorIdade:
        MaiorIdade = idade
    if MenorIdade == 0:
        MenoIdade = idade
    elif MenorIdade > idade:
        MenorIdade = idade
    idade = int(input("idade:"))
percentual18e35 = (mulheres*100)/total
percentual65 = (MaisDe65*100)/total

print("A maior idade é :", MaiorIdade)
print("A menor idade é :", MenorIdade)
print("o percentual de indivíduos do sexo feminino com idade entre 18 e 35 anos é: ", percentual18e35)
print("o percentual de indivíduo com idade maior ou igual a 65 anos é: ", percentual65)
