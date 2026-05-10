'''Nome: Enzo Koji Maciel Matsushita
RA: 32273754
Nome: Victor Hugo Fiuza Garcia
RA: 32270666
Nome: Thaís Canguçu
RA: 32270402'''

print ("Seja muito bem-vindo ao QUIZ sobre Saúde mental!\nVocê se considera mentalmente estável?\nFaça esse teste para verificar seus conhecimentos a respeito da Saúde mental!")
x=0
acertos=0
erros=0

while True:
    iniciar=str(input("\nDigite (INICIAR) para começar o Quiz: "))
    if iniciar=="INICIAR" or iniciar=="Iniciar" or iniciar=="iniciar":
        x+=1
    else:
        while iniciar!="INICIAR" or iniciar!="Iniciar" or iniciar!="iniciar":
            iniciar=str(input("Opção inválida! Digite (INICIAR) para começar: "))
            if iniciar=="INICIAR" or iniciar=="Iniciar" or iniciar=="iniciar":
                x+=1
                break                
    print("\n1)Em qual faixa etária a maioria das doenças mentais começam? \nA)Infância \nB)Adolescência \nC)Adulta")
    questão1=str(input("➩Digite a alternativa: "))
    if questão1=="A" or questão1=="a":
        print ("•Muito bem! Vamos para a próxima pergunta!")
        acertos+=1
    elif questão1=="B" or questão1=="b" or questão1=="C" or questão1=="c":
        print("\n•Explicativa: A maioria das doenças mentais se iniciam na infância! Devido algum evento traumático.")
        erros+=1
    else:
        while questão1!="A" or questão1!="a" or questão1!="B" or questão1!="b" or questão1!="C" or questão1!="c":
            questão1=(input("➩Resposta inválida! Digite novamente a alternativa: "))
            if questão1=="A" or questão1=="a":
                print ("Muito bem! Vamos para a próxima pergunta!")
                acertos+=1
                break            
            elif questão1=="B" or questão1=="b" or questão1=="C" or questão1=="c":
                print("\n•Explicativa: A maioria das doenças mentais se iniciam na infância! Devido algum evento traumático.")
                erros+=1
                break
    print("\n2)Quais das informações abaixo é verdade: \nA) Todos os distúrbios psicológicos levam à loucura. \nB)Procurar ajuda psiquiátrica é inútil, pois nenhum desajuste mental tem cura. \nC)Quanto mais precoce for o tratamento, melhor e mais rápida será a superação da doença. ")
    questão2=str(input("➩Digite a alternativa: "))
    if questão2=="C" or questão2=="c":
        print ("•Muito bem! Vamos para a próxima pergunta!")
        acertos+=1
    elif questão2=="A" or questão2=="a" or questão2=="B" or questão2=="b":
        print("\n•Explicativa: Com o auxílio de um profissional adequado, qualquer problema grave possui um determinado tratamento, que \npossibilita o revigoramento da qualidade de vida do indivíduo.")
        erros+=1
    else:
        while questão2!="A" or questão2!="a" or questão2!="B" or questão2!="b" or questão2!="C" or questão2!="c":
            questão2=(input("➩Resposta inválida! Digite novamente a alternativa: "))
            if questão2=="C" or questão2=="c":
                print ("•Muito bem! Vamos para a próxima pergunta!")
                acertos+=1
                break                
            elif questão2=="A" or questão2=="a" or questão2=="B" or questão2=="b":
                print("\n•Explicativa: Com o auxílio de um profissional adequado, qualquer problema grave possui um determinado tratamento, que \npossibilita o revigoramento da qualidade de vida do indivíduo.")
                erros+=1
                break
            
    print("\n3)Quais dessas opções, apresenta APENAS sintomas de doenças mentais? \nA)Febre, Tosse, Tristeza profunda. \nB)Picos de estresse, Sensibilidade a luz, Desorientação. \nC)Perca de interesse nas atividades, Picos de insônia, Medos excessivos.")
    questão3=str(input("➩Digite a alternativa: "))
    if questão3=="C" or questão3=="c":
        print ("•Muito bem! Vamos para a próxima pergunta!")
        acertos+=1
    elif questão3=="A" or questão3=="a" or questão3=="B" or questão3=="b":
        print("\n•Explicativa: Transtornos mentais são responsáveis por afetar principalmente o comportamento emocional do indivíduo, provocando a \nperda de interesse, falta de autoestima e mudanças no humor.")
        erros+=1
    else:
        while questão3!="A" or questão3!="a" or questão3!="B" or questão3!="b" or questão3!="C" or questão3!="c":
            questão3=(input("➩Resposta inválida! Digite novamente a alternativa: "))
            if questão3=="C" or questão3=="c":
                print ("•Muito bem! Vamos para a próxima pergunta!")
                acertos+=1
                break
            elif questão3=="A" or questão3=="a" or questão3=="B" or questão3=="b":
                print("\n•Explicativa: Transtornos mentais são responsáveis por afetar principalmente o comportamento emocional do indivíduo, provocando a \nperda de interesse, falta de autoestima e mudanças no humor.")
                erros+=1
                break

    print("\n4)Quem pode ser afetado por transtornos mentais? \nA)Pessoas com mais de 17 anos. \nB)Todos. \nC)Apenas pessoas acima de 18 anos.")
    questão4=str(input("➩Digite a alternativa: "))
    if questão4=="B" or questão4=="b":
        print ("•Muito bem! Vamos para a próxima pergunta!")
        acertos+=1
    elif questão4=="A" or questão4=="a" or questão4=="C" or questão4=="c":
        print ("\n•Explicativa: Apesar de adolescentes estarem mais propensos a enfrentar problemas com saúde mental por passarem por fases de mudanças constantes \ncomo entrada na escola, no ensino médio, na faculdade, pressão constante para encontrar um parceiro romântico, exploração da identidade\nsexual e etc. Ao longo da vida, todos nós podemos ser afetados por problemas de saúde mental, de maior ou menor gravidade.")
        erros+=1
    else:
        while questão4!="A" or questão4!="a" or questão4!="B" or questão4!="b" or questão4!="C" or questão4!="c":
            questão4=(input("➩Resposta inválida! Digite novamente a alternativa: "))
            if questão4=="B" or questão4=="b":
                print ("•Muito bem! Vamos para a próxima pergunta!")
                acertos+=1
                break
            elif questão4=="A" or questão4=="a" or questão4=="C" or questão4=="c":
                print ("\n•Explicativa: Apesar de adolescentes estarem mais propensos a enfrentar problemas com saúde mental por passarem por fases de mudanças constantes \ncomo entrada na escola, no ensino médio, na faculdade, pressão constante para encontrar um parceiro romântico, exploração da identidade\nsexual e etc. Ao longo da vida, todos nós podemos ser afetados por problemas de saúde mental, de maior ou menor gravidade.")
                erros+=1
                break

    print("\n5)Depressão é similar a qual das alternativas abaixo? \nA)Tristeza. \nB)Mal-estar. \nC)Nenhuma das alternativas. ")
    questão5=str(input("➩Digite a alternativa: "))
    if questão5=="C" or questão5=="c":
        print ("•Muito bem! Vamos para a próxima pergunta!")
        acertos+=1
    elif questão5=="A" or questão5=="a" or questão5=="B" or questão5=="b":
        print ("\n•Explicativa: A tristeza é um estado esperado e importante para alguns processos e situações pelas quais passamos. Já a depressão é um conjunto de \nsinais que envolvem desde a perda de satisfação com a vida, a perda de libido, de sono, dores nas costas e até o humor depressivo. É algo que\ngoverna a paisagem mental da pessoa, a ponto dela se irritar com alguém alegre por perto")
        erros+=1
    else:
        while questão5!="A" or questão5!="a" or questão5!="B" or questão5!="b" or questão5!="C" or questão5!="c":
            questão5=(input("➩Resposta inválida! Digite novamente a alternativa: "))
            if questão5=="C" or questão5=="c":
                print ("•Muito bem! Vamos para a próxima pergunta!")
                acertos+=1
                break
            elif questão5=="A" or questão5=="a" or questão5=="B" or questão5=="b":
                print ("\n•Explicativa: A tristeza é um estado esperado e importante para alguns processos e situações pelas quais passamos. Já a depressão é um conjunto de \nsinais que envolvem desde a perda de satisfação com a vida, a perda de libido, de sono, dores nas costas e até o humor depressivo. É algo que \ngoverna a paisagem mental da pessoa, a ponto dela se irritar com alguém alegre por perto")
                erros+=1
                break
    print ("\n6)Quais são possíveis causas para uma má saúde mental? \nA)Excesso de uso de eletrônicos; Falta de proatividade; Estresse constante. \nB)Entrada na adolescência; Fatores genéticos; Falta de uma rotina de sonos; Eventos traumáticos. \nC)Não ir à igreja; Ser LGBTQIA+; Pobreza.")
    questão6=str(input("➩Digite a alternativa: "))
    if questão6=="B" or questão6=="b":
        print ("•Muito bem! Vamos para a próxima pergunta!")
        acertos+=1
    elif questão6=="A" or questão6=="a" or questão6=="C" or questão6=="c":
        print("\n•Explicativa: Algumas fases, como a entrada na escola, a adolescência, a menopausa e o envelhecimento, ou acontecimentos e dificuldades, tais como a perda de familiar próximo, o divórcio, o desemprego, a pobreza e fatores genéticos podem ser causade perturbações da saúde mental.")
        erros+=1
    else:
        while questão6!="A" or questão6!="a" or questão6!="B" or questão6!="b" or questão6!="C" or questão6!="c":
            questão6=(input("➩Resposta inválida! Digite novamente a alternativa: "))
            if questão6=="B" or questão6=="b":
                print ("•Muito bem! Vamos para a próxima pergunta!")
                acertos+=1
                break
            elif questão6=="A" or questão6=="a" or questão6=="C" or questão6=="c":
                print("\n•Explicativa: Algumas fases, como a entrada na escola, a adolescência, a menopausa e o envelhecimento, ou acontecimentos e dificuldades, tais como a \nperda de familiar próximo, o divórcio, o desemprego, a pobreza e fatores genéticos podem ser causa de perturbações da saúde mental.")
                erros+=1
                break
    print("\n7)Qual das alternativas abaixo não é verdade? \nA)Transtornos como depressão e ansiedade podem impedir uma pessoa de trabalhar.\nB)Depressão não têm cura. \nC)O abuso de álcool e drogas pode causar transtornos mentais.")
    questão7=str(input("➩Digite a alternativa: "))
    if questão7=="B" or questão7=="b":
        print ("•Muito bem! Vamos para a próxima pergunta!")
        acertos+=1
    elif questão7=="C" or questão7=="c" or questão7=="A" or questão7=="a":
        print("\n•Explicativa: Depressão TEM cura sim. Pode ser tratada com medicamentos ou psicoterapia ou as vezes uma combinação dos dois.\nO tratamento deverá ser sempre procurado,uma vez que a recuperação é tanto mais eficaz quanto precoce for o tratamento.")
        erros+=1

    else:
        while questão7!="A" or questão7!="a" or questão7!="B" or questão7!="b" or questão7!="C" or questão7!="c":
            questão7=str(input("➩Resposta inválida! Digite novamente a alternativa: "))
            if questão7=="B" or questão7=="b":
                print ("Muito bem! Vamos para a próxima pergunta!")
                acertos+=1
                break
            elif questão7=="a" or questão7=="A" or questão7=="C" or questão7=="c":
                print("\n•Explicativa: Depressão TEM cura sim. Pode ser tratada com medicamentos ou psicoterapia ou as vezes uma combinação dos dois.\nO tratamento deverá ser sempre procurado,uma vez que a recuperação é tanto mais eficaz quanto precoce for o tratamento.")
                erros+=1
                break


    print ("\n8)Qual país sofre com o maior índice de pessoas com ansiedade? \nA)Brasil. \nB)China. \nC)EUA.")
    questão8=str(input("➩Digite a alternativa: "))
    if questão8=="A" or questão8=="a":
        print ("•Muito bem! Vamos para a próxima pergunta!")
        acertos+=1
    elif questão8=="B" or questão8=="b" or questão8=="C" or questão8=="c":
        print("\n•Explicativa: Segundo a OMS, mais de 9% da população brasileira sofrem com algum tipo de sintoma decorrente desse transtorno, como medo, insegurança,\nagonia e insônia. Fatores socioeconômicos como desemprego, nível de pobreza, falta de segurança e recessão são \nconsiderados de risco e contribuem diretamente para o aumento desses casos.")
        erros+=1
    else:
        while questão8!="A" or questão8!="a" or questão8!="B" or questão8!="b" or questão8!="C" or questão8!="c":
            questão8=(input("➩Resposta inválida! Digite novamente a alternativa: "))
            if questão8=="A" or questão8=="a":
                print ("•Muito bem! Vamos para a próxima pergunta!")
                acertos+=1
                break
            elif questão8=="B" or questão8=="b" or questão8=="C" or questão8=="c":
                print("\n•Explicativa: Segundo a OMS, mais de 9% da população brasileira sofrem com algum tipo de sintoma decorrente desse transtorno, como medo, insegurança, agonia e insônia. Fatores socioeconômicos como desemprego, nível de pobreza, falta de segurança e recessão são \nconsiderados de risco e contribuem diretamente para o aumento desses casos.")
                erros+=1
                break


    print("\n9)Qual é a causa que mais tira vidas mundialmente nos dias atuais? \nA)Homicídios. \nB)Suicídios. \nC)Desastres naturais.")
    questão9=str(input("➩Digite a alternativa: "))
    if questão9=="B" or questão9=="b":
        print ("•Muito bem! Vamos para a próxima pergunta!")
        acertos+=1
    elif questão9=="A" or questão9=="a" or questão9=="C" or questão9=="c":
        print("\n•Explicativa: Segundo uma estimativa da Organização Mundial da Saúde, 883 mil pessoas se matam no mundo a cada ano. É mais \ngente do que todos os mortos em guerras, vítimas de homicídios e desastres naturais – coisas que, somadas, tiram 669 mil vidas por ano.")
        erros+=1
    else:
        while questão9!="A" or questão9!="a" or questão9!="B" or questão9!="b" or questão9!="C" or questão9!="c":
            questão9=(input("➩Resposta inválida! Digite novamente a alternativa: "))
            if questão9=="B" or questão9=="b":
                print ("•Muito bem! Vamos para a próxima pergunta!")
                acertos+=1
                break
            elif questão9=="A" or questão9=="a" or questão9=="C" or questão9=="c":
                print("\n•Explicativa: Segundo uma estimativa da Organização Mundial da Saúde, 883 mil pessoas se matam no mundo a cada ano. É mais \ngente do que todos os mortos em guerras, vítimas de homicídios e desastres naturais – coisas que, somadas, tiram 669 mil vidas por ano.")
                erros+=1
                break
    print("\n10)Quais dos hábitos abaixo prejudicam a Saúde mental? \nA)Pausas para o lazer, Terapia, Música. \nB)Trabalho excessivo, Redes sociais, Relacionamentos tóxicos. \nC)Meditação, Atividade físicas, Sono de qualidade.")
    questão10=str(input("➩Digite a alternativa: "))
    if questão10=="B" or questão10=="b":
        print ("•Muito bem! Vamos para a próxima pergunta!")
        acertos+=1
    elif questão10=="A" or questão10=="a" or questão10=="C" or questão10=="c":
        print("\n•Explicativa: Embora trabalhar seja importante para o sentimento de autorrealização e necessário para a subsistência, não deve \nofuscar as demais áreas de nossa vida. Reprimir emoções por muito tempo deixa as pessoas estressadas. Adultos devem dormir aproximadamente seis a oito horas por noite. \nQuando o limite mínimo não é alcançado, encontramos dificuldade para executar tarefas simples do dia a dia e gerir o nosso humor.")
        erros+=1
    else:
        while questão10!="A" or questão10!="a" or questão10!="B" or questão10!="b" or questão10!="C" or questão10!="c":
            questão10=(input("➩Resposta inválida! Digite novamente a alternativa: "))
            if questão10=="B" or questão10=="b":
                print ("•Muito bem! Vamos para a próxima pergunta!")
                acertos+=1
                break
            elif questão10=="A" or questão10=="a" or questão10=="C" or questão10=="c":
                print("\n•Explicativa: Embora trabalhar seja importante para o sentimento de autorrealização e necessário para a subsistência, não deve \nofuscar as demais áreas de nossa vida. Reprimir emoções por muito tempo deixa as pessoas estressadas. Adultos devem dormir aproximadamente seis a oito horas por noite. \nQuando o limite mínimo não é alcançado, encontramos dificuldade para executar tarefas simples do dia a dia e gerir o nosso humor.")
                erros+=1
                break
    break

if acertos==0:
    print("\n➩Pontuação: Você teve um total de:",acertos,"acertos e",erros,"erros! \n•Não sabe nada sobre saúde mental! :(")
elif acertos>=1 and acertos<=4:
    print("\n➩Pontuação: Você teve um total de:",acertos,"acertos e",erros,"erros! \n•Precisa estudar mais! :/")
elif acertos>=5 and acertos<=6:
    print("\n➩Pontuação: Você teve o total de:",acertos,"acertos e",erros,"erros! \n•Conhecimento mediano! :P")
elif acertos>=7 and acertos<=9:
    print("\n➩Pontuação: Você teve o total de:",acertos,"acertos e",erros,"erros! \n•Sabe bastante sobre saúde mental! :)")
elif acertos==10:
    print("\n➩Pontuação: Você teve o total de:",acertos,"acertos e",erros,"erros!\n•Um Expert em saúde mental! :D")

    
        
