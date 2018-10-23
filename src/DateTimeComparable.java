import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

/**
 * Date time comparable interface
 * 
 * @author Tim Weaver
 * @version 2018-10-23
 * 
 */

public interface DateTimeComparable 
{
    boolean newerThan(GregorianCalendar inDateTimeUTC);
    boolean olderThan(GregorianCalendar inDateTimeUTC);
    boolean sameAs(GregorianCalendar inDateTimeUTC);
    boolean newerThan(ZonedDateTime inDateTimeUTC);
    boolean olderThan(ZonedDateTime inDateTimeUTC);
    boolean sameAs(ZonedDateTime inDateTimeUTC);
}
