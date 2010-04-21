/***********************************************************************************************************************
 * Copyright (c) 2008 empolis GmbH and brox IT Solutions GmbH. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Daniel Stucky (empolis GmbH) - initial API and implementation
 **********************************************************************************************************************/

package org.eclipse.smila.processing.pipelets.xmlprocessing.test;

import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.eclipse.smila.datamodel.id.Id;
import org.eclipse.smila.datamodel.record.Literal;
import org.eclipse.smila.datamodel.record.RecordFactory;
import org.eclipse.smila.processing.configuration.PipeletConfiguration;
import org.eclipse.smila.processing.pipelets.ATransformationPipelet;
import org.eclipse.smila.processing.pipelets.test.APipeletTest;
import org.eclipse.smila.processing.pipelets.xmlprocessing.TidyPipelet;

/**
 * Test the XslTransformationPipelet.
 */
public class TestTidyPipelet extends APipeletTest {

  /**
   * Constant for the xml document to transform.
   */
  private static final String AUTHOR_HTML = "./configuration/data/author.html";

  /**
   * Constant for the expected html document result.
   */
  private static final String AUTHOR_TIDY_HTML = "./configuration/data/author_tidy.html";

  /**
   * The TidyPipelet.
   */
  private TidyPipelet _pipelet;

  /**
   * {@inheritDoc}
   * 
   * @see org.eclipse.smila.processing.pipelets.test.APipeletTest#setUp()
   */
  protected void setUp() throws Exception {
    super.setUp();
    _pipelet = new TidyPipelet();
  }

  /**
   * Test XslTransformationPipelet with Attributes.
   * 
   * @throws Exception
   *           test fails
   */
  public void testXSLTbyAttribute() throws Exception {
    // load configuration
    final PipeletConfiguration configuration =
      loadPipeletConfiguration("org.eclipse.smila.processing.pipelets.xmlprocessing",
        "TestTidyPipeletByAttribute.xml");
    _pipelet.configure(configuration);

    // prepare test data
    final Id id = createBlackboardRecord("testSource", "testId");
    final Id[] recordIds = new Id[] { id };
    final Literal literal = RecordFactory.DEFAULT_INSTANCE.createLiteral();
    literal.setStringValue(IOUtils.toString(new FileInputStream(AUTHOR_HTML),
      ATransformationPipelet.ENCODING_ATTACHMENT));
    getBlackboard().setLiteral(id, _pipelet.getInputPath(), literal);

    // execute
    _pipelet.process(getBlackboard(), recordIds);

    // check result
    assertTrue(getBlackboard().hasAttribute(id, _pipelet.getOutputPath()));
    final Literal result = getBlackboard().getLiteral(id, _pipelet.getOutputPath());
    assertNotNull(result);
    final String htmlString = result.toString();
    assertNotNull(htmlString);
    final String expected =
      removeBOM(IOUtils.toString(new FileInputStream(AUTHOR_TIDY_HTML), ATransformationPipelet.ENCODING_ATTACHMENT));
    assertEquals(expected, htmlString);
  }

  /**
   * Test XslTransformationPipelet with Attachments.
   * 
   * @throws Exception
   *           test fails
   */
  public void testXSLTbyAttachment() throws Exception {
    // load configuration
    final PipeletConfiguration configuration =
      loadPipeletConfiguration("org.eclipse.smila.processing.pipelets.xmlprocessing",
        "TestTidyPipeletByAttachment.xml");
    _pipelet.configure(configuration);

    // prepare test data
    final Id id = createBlackboardRecord("testSource", "testId");
    final Id[] recordIds = new Id[] { id };
    getBlackboard().setAttachment(id, _pipelet.getInputName(), IOUtils.toByteArray((new FileInputStream(AUTHOR_HTML))));

    // execute
    _pipelet.process(getBlackboard(), recordIds);

    // check result
    assertTrue(getBlackboard().hasAttachment(id, _pipelet.getOutputName()));
    final byte[] result = getBlackboard().getAttachment(id, _pipelet.getOutputName());
    assertNotNull(result);
    final String htmlString = new String(result, ATransformationPipelet.ENCODING_ATTACHMENT);
    assertNotNull(htmlString);
    final String expected =
      removeBOM(IOUtils.toString(new FileInputStream(AUTHOR_TIDY_HTML), ATransformationPipelet.ENCODING_ATTACHMENT));
    assertEquals(expected, htmlString);
  }

  /**
   * Removes the BOM from a UTF-8 String.
   * 
   * @param stringWithBom
   *          the String with BOM
   * @return a String without the BOM
   */
  private String removeBOM(String stringWithBom) {
    return stringWithBom.substring(1);
  }
}
