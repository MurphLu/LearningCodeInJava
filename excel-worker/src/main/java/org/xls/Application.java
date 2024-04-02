package org.xls;

import com.alibaba.excel.read.listener.ReadListener;
import org.apache.commons.codec.Resources;
import org.xls.reader.XlsHandler;
import org.xls.xlsReadListener.PriceDataReadListener;

import java.io.InputStream;
import java.util.ResourceBundle;

public class Application {
    public static void main(String[] args) {
        InputStream inputStream = Resources.getInputStream("t.xls");
        PriceDataReadListener rl = new PriceDataReadListener();
        XlsHandler.handle(inputStream, rl);
        rl.printInfo();
        System.out.println(rl.getDuplicated());
    }
}
