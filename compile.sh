cd src/main
javac -d ../../bin -cp ../ ./Main.java
cd ../../bin
jar -cfm main.jar META-INF/MANIFEST.MF *