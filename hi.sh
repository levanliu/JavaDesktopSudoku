mkdir -p ~/workspace
git clone https://github.com/microsoft/java-debug.git ~/.config/lvim/.java-debug
cd ~/.config/lvim/.java-debug/
./mvnw clean install
git clone https://github.com/microsoft/vscode-java-test.git ~/.config/lvim/.vscode-java-test
cd ~/.config/lvim/.vscode-java-test
npm install
npm run build-plugin
