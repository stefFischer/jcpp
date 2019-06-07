package org.anarres.cpp;

import org.anarres.cpp.featureExpr.FeatureExpression;
import org.anarres.cpp.featureExpr.FeatureExpressionParser;
import org.anarres.cpp.featureExpr.MacroCall;
import org.anarres.cpp.featureExpr.PostOrderTraversal;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;

public class OnlyExandMacrosInIfsController extends PreprocessorControlListener {

    public boolean addMacro(Macro m, Source source) {
        return true;
    }

    public boolean removeMacro(Macro m, Source source) {
        return true;
    }

    public boolean expandMacro(Macro m, Source source, int line, int column, boolean isInIf) {
        return isInIf;
    }

    public boolean include(@Nonnull Source source, int line, @Nonnull String name, boolean quoted, boolean next) {
        return true;
    }

    public boolean processIf(List<Token> condition, Source source, IfType type) {
        if (source instanceof FileLexerSource) {
            if (((FileLexerSource) source).getFile().equals(getFileCurrentlyProcessed())) {
                return false;
            }
        }
        return true;
    }

    public String getPartiallyProcessedCondition(List<Token> condition, Source source, IfType type, final Preprocessor pp) {
        if (source instanceof FileLexerSource) {
            if (((FileLexerSource) source).getFile().equals(getFileCurrentlyProcessed())) {
                if (type == IfType.IF || type == IfType.ELSIF) {

                    FeatureExpressionParser parser = new FeatureExpressionParser(condition);

                    final FeatureExpression expr = parser.parse();

                    expr.traverse(new PostOrderTraversal() {
                        public void postVisit(FeatureExpression visitedExpr) {
                            if(visitedExpr instanceof MacroCall){
                                try {
                                    List<Token> expanded = pp.expand(visitedExpr.toString());
                                    FeatureExpressionParser parser = new FeatureExpressionParser(expanded);
                                    FeatureExpression expandedExpr = parser.parse();
                                    //replace visitedExpr with expandedExpr in expr
                                    expr.replace(visitedExpr, expandedExpr);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (LexerException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    return expr.toString();
                }
            }
        }
        return null;
    }
}
