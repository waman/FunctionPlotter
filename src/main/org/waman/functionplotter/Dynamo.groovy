class Dynamo{

    static final GroovyShell SHELL = new GroovyShell()

    Script functionScript

    Dynamo(String function){
        this.functionScript = SHELL.parse(
"""import static java.lang.Math.*
$function""")
    }

    Object f(x){
        functionScript.x = x
        return functionScript.run()
    }
}