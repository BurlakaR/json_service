package com.jsonservice.parser;

import com.jsonservice.model.JMessage;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Stack;

@Service
public class Parser {

    static private final String START="<html>";
    static private final String HEAD="<head><title>File</title></head>";
    static private final String BODY_START="<body>";
    static private final String BODY_FINISH="</body>";
    static private final String FINISH="</html>";

    private String html;
    private String parseString;
    private Stack<Integer> stackString=new Stack<>();
    private Stack<Integer> massiveString=new Stack<>();
    private int i;

    final static private String[] ban={"\"","},","\\[","]","\\{","}", "<li><p></p></li>","<li></li>", "\\\\"};

    public String parse(JMessage jMessage) {
        parseString = jMessage.getText();
        stackString.push(0);


        html = START+HEAD+BODY_START + find(1)+BODY_FINISH+FINISH;

        for(int j=0;j<ban.length;j++){
        html=html.replaceAll(ban[j], "");
        }

        return html;
    }


    public String addString(int first, int second){
        return "<p>"+parseString.substring(first, second)+"</p>";
    }

    public int more(){
        if(massiveString.empty()) return stackString.peek();
        if(stackString.empty()) return massiveString.peek();
        int x=stackString.peek();
        int y=massiveString.peek();
        if(x>y) return x;
        return y;
    }

    public String find(int start){

        char c=' ';
        String local="";
        int buf;
        boolean base=true;
        try{
        for(i=start;!stackString.empty();i++){
            c=parseString.charAt(i);
            switch (c){
                case '{':
                    buf=more();
                    stackString.push(i);
                    local+=addString(buf, i);
                    local+=find(i+1);
                    base=false;
                    break;

                case '[':
                    buf=more();
                    local+=addString(buf, i);
                    massiveString.push(i);
                    local+=findList(i+1);
                    base=false;
                    break;

                case '}':
                    if(base)
                    local+=addString(stackString.pop(), i);
                    return local;
            }
        }}catch(Exception e){

        }
        return local;
    }

    public String findList(int start){
        char c=' ';
        int buf;
        String local="<ul>";
        String finish="</ul>";
        LinkedList<String> list = new LinkedList<>();
        for(i=start;!stackString.empty();i++){
            if(i==110920){
                System.out.println("Hello");
            }
            c=parseString.charAt(i);
            switch (c){
                case '{':
                    buf=more();
                    stackString.push(i);
                    list.add(addString(buf, i));
                    local+=find(i+1);
                    break;

                case '[':
                    buf=more();
                    list.add(addString(buf, i));
                    massiveString.push(i);
                    list.add(findList(i+1));

                    break;

                case ']':
                    return local+result(list)+finish;
            }
        }
        return result(list);
    }

    public String result(LinkedList<String> list){
        String result="";
        for(int j=0; j<list.size(); j++)
            result+="<li>"+list.get(j)+"</li>";

        return result;
    }
}
