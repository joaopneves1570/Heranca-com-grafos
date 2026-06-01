JC = javac
JVM = java
JFLAGS = -g

MAIN = Main

CLASSES = \
	Main.java \
	Grafo.java \
	GrafoListaAdjacencia.java \
	GrafoMatrizAdjacencia.java \
	GrafoPonderadoMatrizAdjacencia.java

all: classes

classes: $(CLASSES:.java=.class)

%.class: %.java
	$(JC) $(JFLAGS) $<

run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class