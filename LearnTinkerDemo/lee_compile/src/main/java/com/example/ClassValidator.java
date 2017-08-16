package com.example;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import static javax.lang.model.element.Modifier.PRIVATE;

/**
 * Created by Lee on 2017/8/11.
 */

final class ClassValidator {

    static boolean isPrivate(Element annotatedClass) {
        return annotatedClass.getModifiers().contains(PRIVATE);
    }

    static String getClassName(TypeElement type, String packageName) {
        int packageLen = packageName.length() + 1;
        System.err.println("name="+type.getQualifiedName().toString());
        return type.getQualifiedName().toString().substring(packageLen)
                .replace('.', '$');
    }
}
