package com.viraj.crypto.skip.using.diffie.hellman;

import javax.crypto.spec.DHParameterSpec;
import java.math.BigInteger;

/**
 * Created by vkulkarni3 on 11/26/16.
 */
public class Skip {

    private static final String SKIP1024_String = "F488FD584E49DBCD20B49DE49107366B336C380D451D0F7C88B31C7C5" +
            "B2D8EF6F3C923C043F0A55B188D8EBB558CB85D38D334FD7C175743A31D186CDE33212CB52AFF3CE1B1294018118" +
            "D7C84A70A72D686C40319C807297ACA950CD9969FABD00A509B0246D3083D66A45D419F9C7C" +
            "BD894B221926BAABA25EC355E92F78C7";


    private static final BigInteger SKIP1024_MODULE = new BigInteger(SKIP1024_String, 16);

    private static final BigInteger SKIP1024_BASE = BigInteger.valueOf(2);

    public static final DHParameterSpec DH_PARAMETER_SPEC = new DHParameterSpec(SKIP1024_MODULE, SKIP1024_BASE);
}
