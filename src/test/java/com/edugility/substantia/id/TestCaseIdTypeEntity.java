/* -*- mode: Java; c-basic-offset: 2; indent-tabs-mode: nil; coding: utf-8-unix -*-
 *
 * Copyright (c) 2014 Edugility LLC.
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
package com.edugility.substantia.id;

import javax.persistence.*;

import org.junit.*;

import static org.junit.Assert.*;

public class TestCaseIdTypeEntity {

  public TestCaseIdTypeEntity() {
    super();
  }

  @Test
  public void testingJPA() throws Exception {
    final EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
    assertNotNull(emf);

    final EntityManager em = emf.createEntityManager();
    assertNotNull(em);

    final EntityTransaction et = em.getTransaction();
    assertNotNull(et);
    et.begin();


    final IdTypeEntity<Long, Object> idType = new IdTypeEntity<Long, Object>("Fred", Long.class, Object.class);
    em.persist(idType);
    em.flush();

    assertTrue(em.contains(idType));
    assertEquals(Long.valueOf(1L), em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(idType));
    
    et.rollback();

    em.close();

    emf.close();
  }


}
