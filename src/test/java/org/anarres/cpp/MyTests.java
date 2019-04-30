package org.anarres.cpp;

import org.junit.Test;

import javax.annotation.Nonnull;
import java.io.File;

public class MyTests {

    private static final String cfile = "src\\test\\resources\\my\\MinTest.c";

//    private static final String cfile = "src\\test\\resources\\my";

    @Test
    public void testMain() throws Exception {
        Main.main(new String[]{cfile, "-I", "C:/Program Files (x86)/Dev-Cpp/MinGW64/lib/gcc/x86_64-w64-mingw32/4.9.2/include", "-I", "C:/Program Files (x86)/Dev-Cpp/MinGW64/lib/gcc/x86_64-w64-mingw32/4.9.2/include/ssp"});
    }

    private boolean isCurrentSource = true;

    @Test
    public void testPP() throws Exception {

        Preprocessor pp = new Preprocessor();
        pp.addFeature(Feature.DIGRAPHS);
        pp.addFeature(Feature.TRIGRAPHS);
        pp.addFeature(Feature.LINEMARKERS);
        pp.addFeature(Feature.INCLUDENEXT);
        pp.addWarning(Warning.IMPORT);
       // pp.addMacro("__JCPP__");
        pp.getSystemIncludePath().add("/usr/local/include");
        pp.getSystemIncludePath().add("/usr/include");
        pp.getFrameworksPath().add("/System/Library/Frameworks");
        pp.getFrameworksPath().add("/Library/Frameworks");
        pp.getFrameworksPath().add("/Local/Library/Frameworks");

        //include paths specific for this pc
        pp.getSystemIncludePath().add("C:/Program Files (x86)/Dev-Cpp/MinGW64/lib/gcc/x86_64-w64-mingw32/4.9.2/include");
        pp.getSystemIncludePath().add("C:/Program Files (x86)/Dev-Cpp/MinGW64/lib/gcc/x86_64-w64-mingw32/4.9.2/include/ssp");

        final File file = new File(cfile);

        pp.addInput(file);

        pp.setListener(new PreprocessorListener() {
            public void handleWarning(@Nonnull Source source, int line, int column, @Nonnull String msg) throws LexerException {
                System.out.println("WARNING: " + source.getName() + ":" + line + ":" + column + ": warning: " + msg);
            }

            public void handleError(@Nonnull Source source, int line, int column, @Nonnull String msg) throws LexerException {
                System.out.println("ERROR: " + source.getName() + ":" + line + ":" + column + ": warning: " + msg);
            }

            public void handleSourceChange(@Nonnull Source source, @Nonnull SourceChangeEvent event) {
//                System.out.println("SourceChange: " + source + " : event: " + event);
                if(source instanceof  FileLexerSource){
                    if(((FileLexerSource) source).getFile().equals(file)){
                        isCurrentSource = true;
                    } else {
                        isCurrentSource = false;
                    }
                }
            }

            public void handleInclude(@Nonnull String text, Source source, Source toInclude) {

            }
        });

        try {
            for (;;) {
                Token tok = pp.token();
                if (tok == null)
                    break;
                if (tok.getType() == Token.EOF)
                    break;
                if(isCurrentSource){
                    System.out.print(tok.getText());
                }
            }
        } catch (Exception e) {
            StringBuilder buf = new StringBuilder("Preprocessor failed:\n");
            Source s = pp.getSource();
            while (s != null) {
                buf.append(" -> ").append(s).append("\n");
                s = s.getParent();
            }
            System.err.println(buf.toString());
            e.printStackTrace();
        }

        pp.close();

    }

    @Test
    public void testAPI() {
        PreprocessorAPI pp = new PreprocessorAPI();

        //add locations for includes
        pp.addSystemIncludePath("C:/Program Files (x86)/Dev-Cpp/MinGW64/lib/gcc/x86_64-w64-mingw32/4.9.2/include");
        pp.addSystemIncludePath("C:/Program Files (x86)/Dev-Cpp/MinGW64/lib/gcc/x86_64-w64-mingw32/4.9.2/include/ssp");

        //insert code from header files into output?
        pp.setIncludeHeaders(false);

        //keep include directives, even though they will be processed either way
        //NOTE: only set one of the two options at a time
        pp.setKeepIncludes(false);

        //you can set macros that are not defined in the source code
        pp.addMacro("DO_SWAP");

        File src = new File(cfile);
        File target = new File("processed");

        //src file or directory
        //target directory
        pp.preprocess(src, target);
    }
}