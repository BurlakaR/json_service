package com.jsonservice.parser;

import com.jsonservice.model.JMessage;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Stack;

@Service
public class Parser {

    private String html;
    private String parseString;
    private Stack<Integer> stackString=new Stack<>();
    private Stack<Integer> massiveString=new Stack<>();
    private int i;

    final static private String[] ban={"\"","},","\\[","]","\\{","}"};

    public String parse(JMessage jMessage) {
        parseString = jMessage.getText();
        stackString.push(0);
        html = "" + find(1);
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
        for(i=start;i<parseString.length();i++){
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
        String local="";
        LinkedList<String> list = new LinkedList<>();
        for(i=start;i<parseString.length();i++){
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
                    massiveString.push(i);
                    list.add(findList(i+1));

                    break;

                case ']':
                    return local+result(list);
            }
        }
        return result(list);
    }

    public String result(LinkedList<String> list){
        String result="";
        for(int j=0; j<list.size(); j++)
            result+=list.get(j);

        return result;
    }
}
