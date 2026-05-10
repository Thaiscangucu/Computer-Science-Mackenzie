#exercício 8
funcionarios = int(input("insira a quantidade de funcionários: "))
contSalario = 0
salarioH = 0
contMasc = 0
contFem = 0
for x in range(0,funcionarios):
    nome = input("digite seu nome: ")
    sexo = input("digite seu sexo (F/M): ")
    if sexo != "M" and sexo != "F":
        print("Dado inválido")
    elif sexo == "F":
        contFem +=1
    elif sexo == "M":
        contMasc +=1
    salario = int(input("insira seu salario: "))
    if salario < 850:
        print("Dado inválido")    
    elif salario >= 3000:
        salario = salario*0.045
        if sexo == "M":
            salarioH += 1
    elif salario < 3000 and salario >= 2000:
        salario = salario*0.065
        if sexo == "M":
            salarioH +=1
        contSalario += 1 
    else:
        salario = salario*0.085
        if sexo == "M":
            salarioH +=1
            8
salarioH = salarioH/contMasc
contFem = (contFem/funcionarios)*100

print("Empregados que receberm reajuste de 6,5%: ", contSalario)
print("O percentual de empregados do sexo feminino: ", contFem, "%")
print("O salário rejustado médio entre os empregados do sexo masculiino foi de: ", salarioH)
     
     
