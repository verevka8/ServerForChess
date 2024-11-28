package com.spring.boot.serverforchess.session;

import com.spring.boot.serverforchess.websocket.entity.ChessMove;

public record InformationOfChessMove(String userSessionId, ChessMove move) {}
