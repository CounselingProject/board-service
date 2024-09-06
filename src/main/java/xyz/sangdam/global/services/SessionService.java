package xyz.sangdam.global.services;

import lombok.RequiredArgsConstructor;
import xyz.sangdam.global.Utils;
import xyz.sangdam.global.entities.SessionEntity;
import xyz.sangdam.global.repositories.SessionRepository;
import xyz.sangdam.member.MemberUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository repository;
    private final MemberUtil memberUtil;
    private final Utils utils;

    public void save(String key, String value) {
        String token = getToken();
        String _key = String.format("%s_%s", token, key);
        SessionEntity entity = SessionEntity.builder()
                .key(_key)
                .value(value)
                .build();
        repository.save(entity);
    }

    public String get(String key) {
        String _key = String.format("%s_%s", getToken(), key);
        SessionEntity entity = repository.findById(_key).orElse(null);

        return entity == null ? null : entity.getValue();
    }

    public String getToken() {
        return memberUtil.isLogin() ? utils.getToken() : String.valueOf(utils.guestUid());
    }
}
