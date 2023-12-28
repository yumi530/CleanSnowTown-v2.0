package com.project.cleansnowtown.api.service.order;

import com.project.cleansnowtown.domain.Address;
import com.project.cleansnowtown.domain.item.Item;
import com.project.cleansnowtown.domain.item.ItemRepository;
import com.project.cleansnowtown.domain.member.Member;
import com.project.cleansnowtown.domain.member.MemberRepository;
import com.project.cleansnowtown.domain.order.Order;
import com.project.cleansnowtown.domain.order.OrderRepository;
import com.project.cleansnowtown.domain.order_item.OrderItem;
import com.project.cleansnowtown.domain.removal.Removal;
import com.project.cleansnowtown.domain.removal.RemovalStatus;
import com.project.cleansnowtown.dto.order.OrderCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public OrderCreateResponse order(OrderCreateRequest request) {

        // 엔티티 조회
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("회원정보가 없습니다."));//이부분 수정
        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new RuntimeException("상품이 없습니다."));

        // 수거정보 생성
        Removal removal = Removal.builder()
                .address(Address.builder()
                        .city(request.getCity())
                        .street(request.getStreet())
                        .zipCode(request.getZipCode())
                        .build())
                .removalStatus(RemovalStatus.REMOVAL_STATUS_WAIT)
                .build();

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), request.getCount());

        // 주문 생성
        Order order = Order.createOrder(member, removal, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return OrderCreateResponse.of(order);
    }

}
