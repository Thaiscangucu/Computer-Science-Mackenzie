# Computação Visual

## 1. Expectativa vs. Realidade na Disciplina

Minha ideia inicial sobre Computação Visual era de que focaríamos principalmente no estudo de recursos visuais digitais, renderização de imagens e criação de animações — semelhantes às utilizadas no desenvolvimento de jogos. 

---

## 2. A História e o Funcionamento do Sistema de Cores RGB

O **modelo de cores RGB** (*Red, Green, Blue*) é a base fundamental para a exibição de imagens em meios eletrônicos, como monitores, TVs e telas de smartphones. Trata-se de um sistema **aditivo**, no qual a junção das três luzes primárias em suas intensidades máximas resulta na cor branca.

* 📖 **Leitura:** [Artigo sobre o Modelo de Cores RGB (Wikipédia)](https://pt.wikipedia.org/wiki/RGB)
* 🎥 **Vídeo explicativo:** [Vídeo no YouTube](https://youtu.be/N1D6Lv6RYys)

---

## 3. A História e o Funcionamento do Algoritmo de Detecção de Bordas de Canny

Criado por John F. Canny em 1986, o detector de bordas de Canny é um dos algoritmos mais fundamentais e amplamente utilizados em computação visual. Ele identifica descontinuidades acentuadas de brilho em uma imagem para isolar contornos e estruturas relevantes, servindo como etapa inicial indispensável para tarefas como detecção de objetos, rastreamento e reconstrução 3D.

* 📖 **Leitura:** [Artigo sobre o Detector de Bordas de Canny (Wikipédia)](https://pt.wikipedia.org/wiki/Detector_de_bordas_de_Canny)
* 🎥 **Vídeo explicativo:** [Segmentação por bordas - Canny (YouTube)](https://www.youtube.com/watch?v=OsKz8YJ2jqE)

---

## 4. A História e o Funcionamento das Redes Neurais Convolucionais (CNNs)

As **Redes Neurais Convolucionais** (CNNs, do inglês *Convolutional Neural Networks*) são inspiradas no funcionamento do córtex visual dos animais, onde neurônios individuais respondem apenas a estímulos em regiões restritas do campo de visão — os chamados campos receptivos. É uma classe de rede neural artificial do tipo feed-forward, aplicada com sucesso no processamento e análise de imagens digitais, e hoje é um dos pilares da computação visual moderna, usada em tarefas como reconhecimento facial, classificação de objetos e segmentação de imagens.

Diferente das redes neurais tradicionais totalmente conectadas, a CNN usa a operação de **convolução** — filtros que percorrem a imagem capturando padrões locais (bordas, texturas, formas) — o que reduz drasticamente a quantidade de parâmetros a serem treinados e torna o processamento de imagens muito mais viável computacionalmente.

* 📖 **Leitura:** [Artigo sobre Rede Neural Convolucional (Wikipédia)](https://pt.wikipedia.org/wiki/Rede_neural_convolucional)
* 🎥 **Vídeo explicativo:** [O que são Redes Neurais Convolucionais? (YouTube)](https://www.youtube.com/watch?v=sgt_67zNDrI)

---

## 5. A História e o Funcionamento do Ray Tracing (Traçado de Raios)

O **Ray Tracing** é uma técnica de renderização que gera imagens simulando o caminho físico da luz em uma cena 3D. A ideia surgiu em 1968, quando Arthur Appel propôs lançar raios a partir do observador para determinar quais objetos são visíveis (*ray casting*). Em 1980, Turner Whitted ampliou o método com o **ray tracing recursivo**, permitindo calcular reflexos, refrações e sombras de forma realista. Por décadas, o custo computacional restringiu a técnica a filmes e animações renderizados offline, até que a Nvidia lançou em 2018 a família GeForce RTX 20, com núcleos dedicados (RT Cores), levando a técnica aos jogos em tempo real.

O funcionamento básico é o inverso do que ocorre na natureza: em vez de seguir a luz da fonte até o olho, o algoritmo lança um raio **da câmera através de cada pixel** da tela. Ao atingir um objeto, calcula-se a cor naquele ponto considerando as fontes de luz; se a superfície for reflexiva ou transparente, novos raios são disparados recursivamente. Diferente da **rasterização** — que projeta polígonos na tela e usa "truques" para simular iluminação —, o ray tracing produz naturalmente efeitos como reflexos de objetos fora da tela e sombras suaves, ao custo de muito mais processamento.

* 📖 **Leitura:** [Artigo sobre Ray Tracing (Wikipédia)](https://pt.wikipedia.org/wiki/Ray_tracing)
* 🎥 **Vídeo explicativo:** [O que é Ray Tracing? (YouTube)](https://www.youtube.com/results?search_query=o+que+%C3%A9+ray+tracing+como+funciona)

---

## 6. Fotogrametria: Transformando Fotos em Modelos 3D

A **fotogrametria** é a técnica de reconstruir objetos e ambientes tridimensionais a partir de um conjunto de fotografias comuns. Basta fotografar um objeto (uma estátua, um prédio, até uma pessoa) de vários ângulos diferentes, e o computador consegue "deduzir" sua forma e textura, gerando um modelo 3D digital. Hoje ela é usada em jogos, efeitos visuais de filmes, preservação de patrimônio histórico, arqueologia, mapeamento com drones e até em aplicativos de celular que escaneiam objetos.

O processo combina várias ideias de computação visual:

1. **Detecção de pontos de interesse:** o algoritmo encontra pontos marcantes em cada foto (cantos, manchas, detalhes de textura), usando métodos como o **SIFT**.
2. **Correspondência entre imagens:** esses pontos são comparados entre as fotos para descobrir quais deles representam o mesmo ponto do objeto real.
3. **Structure from Motion (SfM):** com as correspondências, o sistema calcula ao mesmo tempo a posição de cada câmera e a posição 3D dos pontos, por triangulação — o mesmo princípio que nossos dois olhos usam para perceber profundidade.
4. **Nuvem de pontos e malha:** os pontos formam uma nuvem densa, que é convertida em uma malha de polígonos e recebe a textura extraída das próprias fotos.

Um detalhe curioso é que a técnica tem limitações bem visíveis: superfícies **transparentes, espelhadas ou sem textura** (como vidro, metal polido ou uma parede branca lisa) confundem o algoritmo, pois não há pontos distintos para comparar entre as imagens. Mais recentemente, técnicas baseadas em redes neurais, como **NeRF** (*Neural Radiance Fields*) e **Gaussian Splatting**, surgiram como evolução dessa ideia, conseguindo representar até reflexos e transparências com realismo impressionante.

* 📖 **Leitura:** [Artigo sobre Fotogrametria (Wikipédia)](https://pt.wikipedia.org/wiki/Fotogrametria)
* 🎥 **Vídeo explicativo:** [O que é Fotogrametria? (YouTube)](https://www.youtube.com/results?search_query=fotogrametria+como+funciona+modelo+3D)

---

## 7. A História e o Funcionamento da Compressão de Imagens JPEG

O **JPEG** é o formato de imagem mais usado no mundo, presente em câmeras digitais, celulares e na maior parte das fotos da internet. O nome vem do *Joint Photographic Experts Group*, comitê criado em 1986 para definir um padrão de compressão de fotografias. O padrão foi publicado em 1992. Sua grande sacada é ser uma compressão **com perdas** (*lossy*): ele descarta informações que o olho humano dificilmente percebe, reduzindo o tamanho do arquivo em até 10 vezes sem perda visível de qualidade.

O processo explora as limitações da nossa visão em algumas etapas:

1. **Conversão de cor (RGB → YCbCr):** a imagem é separada em **luminância** (brilho, Y) e **crominância** (cor, Cb e Cr). Nossos olhos são muito mais sensíveis a variações de brilho do que de cor.
2. **Subamostragem de crominância:** aproveitando essa característica, os canais de cor são armazenados em resolução menor (por exemplo, metade da largura e da altura), quase sem diferença perceptível.
3. **Divisão em blocos 8×8 e DCT:** a imagem é dividida em blocos de 8×8 pixels, e cada bloco passa pela **Transformada Discreta do Cosseno (DCT)**. A DCT reescreve o bloco como uma soma de padrões de frequência, das variações suaves às mais finas.
4. **Quantização:** esta é a etapa onde ocorre a perda. As altas frequências (detalhes finos que mal enxergamos) são arredondadas de forma agressiva, e muitas viram zero. O controle de "qualidade" ao salvar um JPEG ajusta justamente essa etapa.
5. **Codificação de entropia:** os coeficientes são lidos em **zigue-zague**, agrupando os zeros, e comprimidos sem perdas com *run-length* e **codificação de Huffman**.

Quando a compressão é exagerada, surgem os famosos **artefatos de compressão**. Os blocos 8×8 ficam visíveis (*blocking*) e aparecem "fantasmas" ao redor de bordas nítidas (*ringing*). Por isso o JPEG funciona mal com textos, logos e desenhos com traços definidos, para os quais o **PNG** é mais indicado. Formatos mais modernos, como **WebP**, **AVIF** e **JPEG XL**, buscam a mesma qualidade com arquivos ainda menores.

* 📖 **Leitura:** [Artigo sobre JPEG (Wikipédia)](https://pt.wikipedia.org/wiki/JPEG)
* 🎥 **Vídeo explicativo:** [Como funciona a compressão JPEG? (YouTube)](https://www.youtube.com/results?search_query=como+funciona+compress%C3%A3o+jpeg+dct)

---