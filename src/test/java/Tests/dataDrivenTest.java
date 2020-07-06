package Tests;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class dataDrivenTest {

    @Test

    public void Test1() throws IOException {
        ArrayList data = dataDriven.getData("Purchase");
        System.out.println(data.get(1));
    }
}
