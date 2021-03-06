package it.polimi.chansonnier.processing;

import it.polimi.chansonnier.spi.FuzzyResult;
import it.polimi.chansonnier.spi.LanguageRecognitionService;

import org.eclipse.smila.blackboard.Blackboard;
import org.eclipse.smila.blackboard.BlackboardAccessException;
import org.eclipse.smila.blackboard.path.Path;
import org.eclipse.smila.datamodel.id.Id;
import org.eclipse.smila.datamodel.record.Literal;
import org.eclipse.smila.processing.ProcessingException;
import org.eclipse.smila.processing.ProcessingService;

public class LanguageProcessingService extends AbstractProcessingService implements ProcessingService {
	private LanguageRecognitionService _driver;

	@Override
	public Id[] process(Blackboard blackboard, Id[] recordIds)
			throws ProcessingException {
		
		try {
			for (Id id : recordIds) {
				Path p = new Path(getInputPath(blackboard, id));
				Literal lyrics = blackboard.getLiteral(id, p);
				FuzzyResult language = _driver.getLanguage(lyrics.toString());
				Literal value = blackboard.createLiteral(id);
				value.setStringValue(language.getValue());
				blackboard.addLiteral(id, new Path(getOutputPath(blackboard, id)), value);
				Literal confidence = blackboard.createLiteral(id);
				confidence.setFpValue(language.getConfidence());
				blackboard.addLiteral(id, new Path(getOutputPath(blackboard, id) + "Confidence"), confidence);
			}
		} catch (BlackboardAccessException e) {
			throw new ProcessingException(e);
		}
		return recordIds;
	}

	public void setLanguageRecognitionService(LanguageRecognitionService driver) {
		_driver = driver;
	}

}
