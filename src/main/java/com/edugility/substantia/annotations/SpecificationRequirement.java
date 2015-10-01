/* -*- mode: Java; c-basic-offset: 2; indent-tabs-mode: nil; coding: utf-8-unix -*-
 *
 * Copyright (c) 2014-2015 Edugility LLC.
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense and/or sell copies
 * of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT.  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 * The original copy of this license is available at
 * http://www.opensource.org/license/mit-license.html.
 */
package com.edugility.substantia.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated element exists because of a
 * specification requirement.
 *
 * @author <a href="http://about.me/lairdnelson/"
 * target="_parent">Laird Nelson</a>
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({
  ElementType.CONSTRUCTOR, 
  ElementType.FIELD,
  ElementType.LOCAL_VARIABLE,
  ElementType.METHOD,
  ElementType.PACKAGE,
  ElementType.PARAMETER,
  ElementType.TYPE
})
public @interface SpecificationRequirement {

  /**
   * The name of the specification.
   *
   * @return the name of the specification
   */
  String name() default "";

  /**
   * The version of the specification.
   *
   * @return the version of the specification
   */
  String version() default "";

  /**
   * The section of the specification.
   *
   * @return the section of the specification
   */
  String section() default "";
  
  /**
   * The description of the requirement (ideally a citation from the
   * specification).
   *
   * @return the description of the requirement
   */
  String description() default "";
  
}
