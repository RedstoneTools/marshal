package tools.redstone.marshal.exceptions;

import tools.redstone.marshal.Parsable;

import java.util.ArrayList;
import java.util.List;

public class ParseException extends MarshalException {
    public static class Builder {
        private final Parsable<?, ?> parserTheFailureWasCausedIn;
        private List<Parsable<?, ?>> subParsers;
        private String got;

        private Builder(Parsable<?, ?> parserTheFailureWasCausedIn) {
            this.parserTheFailureWasCausedIn = parserTheFailureWasCausedIn;

            subParsers = new ArrayList<>();
        }

        public static Builder whileTryingToParse(Parsable<?, ?> parser) {
            return new Builder(parser);
        }

        public Builder addSubParsers(List<Parsable<?, ?>> subParsers) {
            this.subParsers.addAll(subParsers);

            this.subParsers = this.subParsers.stream()
                    .distinct()
                    .toList();

            return this;
        }

        public Builder got(String got) {
            if (this.got != null) {
                // TODO: Is this the right way to do this? Write a better error message if so
                throw new RuntimeException("Already set got");
            }

            this.got = got;

            return this;
        }

        public ParseException build() {
            if (got == null) {
                // TODO: Is this the right way to do this? Write a better error message if so
                throw new RuntimeException("Got not set");
            }

            StringBuilder sb = new StringBuilder();

            // Expected {a}, got {b}
            // Expected either {a}, {b}, or {c}, got {d}

            sb.append("Expected ");

            if (subParsers.size() > 1) {
                sb.append("either ");
            }

            for (int i = 0; i < subParsers.size(); i++) {
                var subParser = subParsers.get(i);

                if (subParsers.size() > 1 && i == subParsers.size() - 1) {
                    sb.append("or ");
                }

                sb.append(subParser.getGenericDisplayName());
                sb.append(", ");
            }

            sb.setLength(sb.length() - 2);

            if (parserTheFailureWasCausedIn != null) {
                sb.append(" while trying to parse ");
                sb.append(parserTheFailureWasCausedIn.getGenericDisplayName());
            }

            sb.append(", got \"");
            sb.append(got);
            sb.append("\".");

            return new ParseException(sb.toString());
        }
    }

    public ParseException(String message) {
        super(message);
    }
}
