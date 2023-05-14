// 
// 
// 

package utils;

import java.math.BigDecimal;

public class PriceUtil
{
    public static float add(final float a, final float b) {
        final BigDecimal bigA = new BigDecimal(Float.toString(a));
        final BigDecimal bigB = new BigDecimal(Float.toString(b));
        return bigA.add(bigB).floatValue();
    }
    
    public static double add(final double a, final double b) {
        final BigDecimal bigA = new BigDecimal(Double.toString(a));
        final BigDecimal bigB = new BigDecimal(Double.toString(b));
        return bigA.add(bigB).doubleValue();
    }
    
    public static float subtract(final float a, final float b) {
        final BigDecimal bigA = new BigDecimal(Float.toString(a));
        final BigDecimal bigB = new BigDecimal(Float.toString(b));
        return bigA.subtract(bigB).floatValue();
    }
    
    public static double subtract(final double a, final double b) {
        final BigDecimal bigA = new BigDecimal(Double.toString(a));
        final BigDecimal bigB = new BigDecimal(Double.toString(b));
        return bigA.subtract(bigB).doubleValue();
    }
}
