package com.alexshin.tennisscoreboard.servlet;

import com.alexshin.tennisscoreboard.exception.service.NoSuchMatchException;
import com.alexshin.tennisscoreboard.mapper.MatchMapper;
import com.alexshin.tennisscoreboard.model.MatchModel;
import com.alexshin.tennisscoreboard.service.MatchScoreCalculationService;
import com.alexshin.tennisscoreboard.service.OngoingMatchesService;
import com.alexshin.tennisscoreboard.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.UUID;

import static com.alexshin.tennisscoreboard.util.ParseParams.parsePlayerNum;
import static com.alexshin.tennisscoreboard.util.ParseParams.parseUUID;

@WebServlet(name = "match-score-servlet", urlPatterns = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    private final MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();
    private final Logger logger = LogManager.getLogger();
    private final MatchMapper mapper = new MatchMapper();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        logger.info("Received GET on /match-score");
        try {
            UUID uuid = parseUUID(req.getParameter("uuid"));
            MatchModel match = ongoingMatchesService.getCurrentMatch(uuid);

            req.setAttribute("matchScore", mapper.toScoreDto(match));
            req.setAttribute("uuid", uuid);
            logger.info("Forward to match-score.jsp");
            req.getRequestDispatcher(JspHelper.getPath("match-score")).forward(req, resp);

        } catch (NoSuchMatchException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            req.setAttribute("error_info", e.getMessage());
            req.getRequestDispatcher(JspHelper.getPath("error")).forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("Received POST on /match-score");
        try {

            UUID uuid = parseUUID(req.getParameter("uuid"));
            int wonPointPlayerNum = parsePlayerNum(req.getParameter("player-won-num"));
            MatchModel match = ongoingMatchesService.getCurrentMatch(uuid);
            logger.info("Got match with uuid=" + match.getUuid());

            matchScoreCalculationService.updateMatchScore(match, wonPointPlayerNum);
            logger.info("Add point to player " + wonPointPlayerNum);

            if (MatchScoreCalculationService.isMatchFinished(match)) {
                ongoingMatchesService.saveMatch(match);

                req.setAttribute("matchScore", mapper.toScoreDto(match));

                logger.info("Forward to match-end.jsp");
                req.getRequestDispatcher(JspHelper.getPath("match-end")).forward(req, resp);
                return;
            }

            String redirectURL = "/match-score?uuid=%s".formatted(match.getUuid());
            logger.info("Redirect to /match-score");
            resp.sendRedirect(req.getContextPath() + redirectURL);


        } catch (NoSuchMatchException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            req.setAttribute("error_info", e.getMessage());
            req.getRequestDispatcher(JspHelper.getPath("error")).forward(req, resp);
        }


    }
}
