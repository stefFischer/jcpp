/*
 * Anarres C Preprocessor
 * Copyright (c) 2007-2015, Shevek
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.anarres.cpp;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * A handler for preprocessor events, primarily errors and warnings.
 *
 * If no PreprocessorListener is installed in a Preprocessor, all
 * error and warning events will throw an exception. Installing a
 * listener allows more intelligent handling of these events.
 */
public interface PreprocessorControlListener {


    boolean expandMacro(Macro m, Source source, int line, int column);

    boolean addMacro(Macro m, Source source);

    boolean removeMacro(Macro m, Source source);

    boolean include(@Nonnull Source source, int line, @Nonnull String name, boolean quoted, boolean next);

    enum IfType{
        IF, IFDEF, IFNDEF, ELSIF
    }

    boolean processIf(List<Token> condition, Source source, IfType type);

    String getPariallyProcessedCondition(List<Token> condition, Source source, IfType type, Preprocessor pp);
}
