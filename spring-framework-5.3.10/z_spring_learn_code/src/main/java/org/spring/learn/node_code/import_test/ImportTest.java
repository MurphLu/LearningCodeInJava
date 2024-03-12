package org.spring.learn.node_code.import_test;

import org.springframework.context.annotation.Import;

@Import({MyImport.class, MyImportRes.class})
public class ImportTest {
}
