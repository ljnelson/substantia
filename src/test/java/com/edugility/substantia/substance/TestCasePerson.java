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
package com.edugility.substantia.substance;

import java.io.IOException;
import java.io.Serializable;

import javax.persistence.*;

import com.edugility.nomen.Name;
import com.edugility.nomen.NameType;
import com.edugility.nomen.NameValue;

import com.edugility.substantia.id.AtomicLongTableScopedIdType;

import com.fasterxml.jackson.databind.ObjectMapper;  

import org.junit.*;

import static org.junit.Assert.*;

public class TestCasePerson {

  public TestCasePerson() {
    super();
  }

  @Test
  public void testJacksonMarshaling() throws IOException {
    final ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new FredModule());
    final Person p = mapper.readValue("{ \"names\" : { \"firstName\" : \"Fred\"}  }", Person.class);
    final Name n = p.getName(new NameType("firstName"));
    assertNotNull(n);
    assertSame(p, n.getNamed());
    final NameValue nv = n.getNameValue();
    assertNotNull(nv);
    assertEquals("Fred", nv.getValue());
    mapper.writeValue(System.out, p);
  }

  @Test
  public void testGenerics() {
    final LoaderException e = new LoaderException(Person.class, Long.valueOf(2L), Integer.valueOf(0), null, null);
    final Serializable id = e.getId();
    final Comparable<?> version = e.getVersion();
    final Class<? extends Substance<? extends Serializable, ?>> type = e.getType();
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

    final Person p = new Person();
    em.persist(p);
    em.flush();
    assertFalse(p.isTransient());
    assertTrue(p.isVersioned());
    assertEquals(Long.valueOf(1L), p.getId());
    assertEquals(Integer.valueOf(1), p.getVersion());

    et.commit();
    et.begin();

    final Person p2 = em.find(Person.class, 1L, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
    assertNotNull(p2);
    assertFalse(p2.isTransient());
    assertTrue(p2.isVersioned());
    assertEquals(Long.valueOf(1L), p2.getId());
    assertEquals(Integer.valueOf(1), p2.getVersion());
    
    et.commit();
    et.begin();

    final Person p3 = em.getReference(Person.class, 1L);
    assertNotNull(p3);
    assertFalse(p3.isTransient());
    assertTrue(p3.isVersioned());
    assertEquals(Long.valueOf(1L), p3.getId());
    assertEquals(Integer.valueOf(2), p3.getVersion());

    et.commit();
    et.begin();

    assertTrue(em.contains(p));
    em.remove(p);

    et.commit();

    em.close();

    emf.close();
  }


}
