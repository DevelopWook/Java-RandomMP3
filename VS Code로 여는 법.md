# VS Code로 여는 법

- 기본적으로 jdk등 설치해서 JAVA_HOME으로 환경설정 해주고
- vscode settings.json에 아래의 내용을 추가해준다.

```json
"java.home": "C:/Program Files/Java/jdk1.8.0_201",  
"files.exclude": {  
    "**/.classpath": true,  
    "**/.project": true,  
    "**/.settings": true,  
    "**/.factorypath": true  
},
```
