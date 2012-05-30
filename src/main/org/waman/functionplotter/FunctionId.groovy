package org.waman.functionplotter

class FunctionId{

    private String name = 'f'
    private int number = 0
    private boolean monoCharacter = true

    def next(){
        if(monoCharacter){
            if(name == 'z'){
                name = 'f'
                monoCharacter = false
            }else{
                name++
            }
        }else{
            number++
        }
        return this
    }

    @Override
    String toString(){
        if(monoCharacter)
            return name
        else
            return name + number.toString()
    }
}