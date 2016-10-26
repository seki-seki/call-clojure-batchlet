package batchlet;

import java.util.Arrays;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.context.StepContext;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

/**
 * 
 * @author yuki seki
 *
 */
public class CallClojureBatchlet extends AbstractBatchlet {
    private final static Logger logger = LoggerFactory.getLogger(CallClojureBatchlet.class);

    @Any
    @Inject
    StepContext stepContext;

    @Override
    public String process() throws Exception {
        String ns = stepContext.getProperties().getProperty("ns");
        String functionName = stepContext.getProperties().getProperty("functionName");
        String[] args = stepContext.getProperties().getProperty("args").split("\\s+");
        callClojure(ns, functionName, args);
        return BatchStatus.COMPLETED.name();
    }

    void callClojure(String ns, String functionName, String... args) {
        logger.info("call clojure [ns = {}] [function name = {}] [args = {}]", ns, functionName,
                String.join(" ", args));
        Clojure.var("clojure.core/require").invoke(Clojure.read(ns));
        IFn func = Clojure.var(ns, functionName);
        switch (args.length) {
        case 0:func.invoke();break;
        case 1:func.invoke(args[0]);break;
        case 2:func.invoke(args[0],args[1]);break;
        case 3:func.invoke(args[0],args[1],args[2]);break;
        case 4:func.invoke(args[0],args[1],args[2],args[3]);break;
        case 5:func.invoke(args[0],args[1],args[2],args[3],args[4]);break;
        case 6:func.invoke(args[0],args[1],args[2],args[3],args[4],args[5]);break;
        case 7:func.invoke(args[0],args[1],args[2],args[3],args[4],args[5],args[6]);break;
        case 8:func.invoke(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7]);break;
        case 9:func.invoke(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8]);break;
        case 10:func.invoke(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9]);break;
        case 11:func.invoke(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10]);break;
        case 12:func.invoke(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11]);break;
        case 13:func.invoke(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12]);break;
        case 14:func.invoke(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13]);break;
        case 15:func.invoke(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14]);break;
        case 16:func.invoke(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15]);break;
        case 17:func.invoke(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16]);break;
        case 18:func.invoke(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17]);break;
        case 19:func.invoke(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18]);break;
        case 20:func.invoke(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19]);break;
        default:func.invoke(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],Arrays.copyOfRange(args, 20, args.length-1));break;
        }
    }

    /**
     * create swith sentence
     * 
     * @param args
     */
    public static void main(String[] args) {
        //TODO make 20 constant
        // create case
        for (int i = 0; i <= 20; i++) {
            System.out.print(String.format("case %d:func.invoke(", i));
            for (int j = 0; j < i; j++) {
                System.out.print(String.format("args[%d]", j));
                if(j!=i-1)System.out.print(",");
            }
            System.out.println(");break;");
        }
        // creaet default
        System.out.print("default:func.invoke(");
        for (int j = 0; j < 20; j++) {
            System.out.print(String.format("args[%d],", j));
        }
        System.out.println("Arrays.copyOfRange(args, 20, args.length-1));break;");
    }

}


