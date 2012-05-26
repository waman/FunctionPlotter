class Dynamo{

    static final GroovyShell SHELL = new GroovyShell()

    Script functionScript

    Dynamo(String function){
        def expr = """
import static java.lang.Math.*
import static org.apache.commons.math3.special.Beta.*
import static org.apache.commons.math3.special.Gamma.*
import static org.apache.commons.math3.special.Erf.*
$function"""
        this.functionScript = SHELL.parse(expr)
    }

    def f(x){
        functionScript.x = x
        return functionScript.run()
    }
}